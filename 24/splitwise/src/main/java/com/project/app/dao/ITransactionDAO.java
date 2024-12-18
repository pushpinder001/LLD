package com.project.app.dao;

import com.project.app.entity.Group;
import com.project.app.entity.Transaction;

import java.util.List;

public interface ITransactionDAO {
    int save(Transaction transaction);

    List<Transaction> getAllTxnByGroupId(int id);
}
