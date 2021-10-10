package com.techelevator;

import com.techelevator.view.Chip;
import com.techelevator.view.Product;
import com.techelevator.view.VendingMachine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineTest {

        BigDecimal FIVE = new BigDecimal(5.00);
        BigDecimal TEN = new BigDecimal(10.00);
        BigDecimal FIFTEEN = new BigDecimal(15.00);
       // BigDecimal testCost = new BigDecimal(1.50);
        VendingMachine vendingMachine = new VendingMachine();


        @Before
        @Test
        public void insert_money(){
            vendingMachine.feedMoney(FIVE);
            Assert.assertEquals(FIVE, vendingMachine.getBalance());
        }
        @Test
        public void check_balance_with_multiple_inserts(){
        vendingMachine.feedMoney(TEN);
        vendingMachine.feedMoney(FIVE);
        Assert.assertEquals(FIFTEEN, vendingMachine.getBalance());
        }

        @Test
        public void display_product(){


        }


    }

