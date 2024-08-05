/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.depositmoney;

import Model.TempTransaction;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dat
 */
public class TempTransactionStore {
    private static Map<String, TempTransaction> transactionMap = new HashMap<>();

    public static void addTransaction(String txnRef, TempTransaction transaction) {
        transactionMap.put(txnRef, transaction);
    }

    public static TempTransaction getTransaction(String txnRef) {
        return transactionMap.get(txnRef);
    }

    public static void removeTransaction(String txnRef) {
        transactionMap.remove(txnRef);
    }
}
