package fr.afcepf.al32.wspromotemplate.dao.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import fr.afcepf.al32.wspromotemplate.dao.IPromotionDao;
import fr.afcepf.al32.wspromotemplate.entity.*;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class PromotionDao implements IPromotionDao {

    @Autowired
    private MongoOperations mongoOps;

    public List<PromotionResultDto> getTopFivePromotionByShopKeeper(Point sourcePoint, String[] categories){

        MongoCollection<Document> promotionCollection = mongoOps.getCollection("promotions");

        String geoNearStep = String.format("{$geoNear: {near: { type: 'Point', coordinates: [ %f , %f ] }, distanceField: 'dist', key: 'shops.location', maxDistance: 2, num: 5 }}", sourcePoint.getX(), sourcePoint.getY());
        Document geoNear = Document.parse(geoNearStep);

        LocalDateTime dateTime = LocalDateTime.now().minusMonths(3L);

        String matchStep = String.format("{$match: {$and: [{dateCreation: {$gte: '%s'}}, {$or: [{'product.category.libelle': {$in: %s}}, {'product.category.ancestors': {$in: %s}}]}]}}", dateTime.toString(), Arrays.toString(categories), Arrays.toString(categories));

        Document match = Document.parse(matchStep);

        Document exclusionProjection = Document.parse("{$project: { dateCreation: 0, isCumulative: 0, shops: 0, 'product.addDate': 0 }}");

        Document inclusionProjection = Document.parse("{$project: { promotionId: 1, name : 1, description : 1, limitTimePromotion : 1, limitTimeTakePromotion : 1, reservedProductPercentage : 1, dist: 1, promotionType : 1, product : 1, _class : 1, timestamp: 1, reservationsNumber: {$size: '$reservations'} }}");

        Document group = Document.parse("{$group: { _id : {promotionId: '$promotionId', name : '$name', description : '$description', limitTimePromotion : '$limitTimePromotion', limitTimeTakePromotion : '$limitTimeTakePromotion', reservedProductPercentage : '$reservedProductPercentage', dist: '$dist', promotionType : '$promotionType', product : '$product', _class : '$_class', reservationsNumber: '$reservationsNumber'}, timestamp : {$max: '$timestamp'}} }");

        Document replaceRoot = Document.parse("{$replaceRoot: {newRoot: '$_id'}}");

        Document sort = Document.parse("{$sort: {reservedProductPercentage: -1, reservationsNumber: -1, dist: 1}}");

        Document limit = Document.parse("{$limit: 5}");

        List<Document> aggregationList = new ArrayList<>();

        aggregationList.add(geoNear);
        aggregationList.add(match);
        aggregationList.add(exclusionProjection);
        aggregationList.add(inclusionProjection);
        aggregationList.add(group);
        aggregationList.add(replaceRoot);
        aggregationList.add(sort);
        aggregationList.add(limit);

        AggregateIterable iterable = promotionCollection.aggregate(aggregationList);

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<PromotionResultDto> result = new ArrayList<>();

        JsonWriterSettings settings = JsonWriterSettings.builder()
                .int64Converter((value, writer) -> writer.writeNumber(value.toString()))
                .build();


        iterable.forEach((Block<Document>) document -> {
            try {
                result.add(mapper.readValue(document.toJson(settings), PromotionResultDto.class));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return result;
    }
}
