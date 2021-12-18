package com.rkmgithubacc.movieapp.service;

import com.rkmgithubacc.movieapp.entity.DbSequence;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class DbSequenceGenServiceImpl implements DbSequenceGenService {
    private final MongoOperations mongoOperation;

    public DbSequenceGenServiceImpl(MongoOperations mongoOperation) {
        this.mongoOperation = mongoOperation;
    }

    @Override
    public int generateSequence(String key) {
        //get sequence id
        Query query = new Query(Criteria.where("_id").is(key));

        //increase sequence id by 1
        Update update = new Update();
        update.inc("sequence", 1);

        //return new increased id
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true).upsert(true);

        //this is the magic happened.
        DbSequence dbSequence =
                mongoOperation.findAndModify(query, update, options, DbSequence.class);

        return !Objects.isNull(dbSequence) ? dbSequence.getSequence() : 1;
    }
}
