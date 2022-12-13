package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;

import android.content.Context;
import android.support.annotation.Nullable;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.database.SQLite;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.impl.PersistentTransactionDAO;

public class PersistentExpenseManager extends ExpenseManager {
    private SQLite embedded_db;
    public PersistentExpenseManager(@Nullable Context context){
        this.embedded_db = new SQLite(context);
        try{
            setup();
        }catch(Exception e){
            System.out.println("Database error");
        }

    }
    @Override
    public void setup() throws ExpenseManagerException {
        TransactionDAO persistentTransactionDAO = new PersistentTransactionDAO(this.embedded_db);
        setTransactionsDAO(persistentTransactionDAO);

        AccountDAO persistentAccountDAO = new PersistentAccountDAO(this.embedded_db);
        setAccountsDAO(persistentAccountDAO);
    }
}