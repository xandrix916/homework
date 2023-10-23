package edu.hw3;

import org.jetbrains.annotations.NotNull;
import java.util.PriorityQueue;

public class Problem6 {
    public static class Stock implements Comparable<Stock> {
        private final String name;
        private double stockPrice;

        public Stock(String name, double price) {
            this.name = name;
            this.stockPrice = price;
        }

        public String getName() {
            return name;
        }

        public void updatePrice(double newPrice) {
            stockPrice = newPrice;
        }

        public double getStockPrice() {
            return stockPrice;
        }

        @Override
        public int compareTo(@NotNull Stock otherStock) {
            double delta = this.stockPrice - otherStock.stockPrice;
            if (delta != 0d) {
                return (int) - delta;
            }
            return this.name.compareTo(otherStock.name);
        }
    }

    @SuppressWarnings("unused")
    public interface StockMarket {
        void add(Stock stock);

        void remove(Stock stock);

        Stock mostValuableStock();
    }

    public static class MoscowExchange implements StockMarket {
        private final PriorityQueue<Stock> stockQueue = new PriorityQueue<>();

        @Override
        public void add(Stock stock) {
            stockQueue.add(stock);
        }

        @Override
        public void remove(Stock stock) {
            stockQueue.remove(stock);
        }

        @Override
        public Stock mostValuableStock() {
            return stockQueue.peek();
        }

        public String closeExchange() {
            Stock currentStock;
            StringBuilder closeLog = new StringBuilder();
            int index = 1;
            while ((currentStock = stockQueue.poll()) != null) {
                closeLog.append("#%d %s %f₽\n".formatted(index, currentStock.getName(), currentStock.getStockPrice()));
                index++;
            }
            return closeLog.toString();
        }
    }

    public String exchangeExample() {
        StringBuilder exchangeLog = new StringBuilder();

        Stock bankSber = new Stock("SBER", 271.54);
        Stock bankVTB = new Stock("VTBR", 0.02609);
        Stock bankTinkoff = new Stock("TCSG", 3490.5);
        Stock groupYandex = new Stock("YNDX", 2674.4);
        Stock groupGazprom = new Stock("GAZP", 170.51);

        MoscowExchange moscowExchange = new MoscowExchange();
        moscowExchange.add(groupGazprom);
        exchangeLog.append("%s stock added. Current price is %f\n".formatted(groupGazprom.getName(), groupGazprom.getStockPrice()));

        moscowExchange.add(bankVTB);
        exchangeLog.append("%s stock added. Current price is %f\n".formatted(bankVTB.getName(), bankVTB.getStockPrice()));
        exchangeLog.append("Most valuable stock is %s, its price is %f\n".formatted(moscowExchange.mostValuableStock().getName(), moscowExchange.mostValuableStock().getStockPrice()));

        moscowExchange.add(bankSber);
        exchangeLog.append("%s stock added. Current price is %f\n".formatted(bankSber.getName(), bankSber.getStockPrice()));
        exchangeLog.append("Most valuable stock is %s, its price is %f\n".formatted(moscowExchange.mostValuableStock().getName(), moscowExchange.mostValuableStock().getStockPrice()));

        moscowExchange.add(groupYandex);
        exchangeLog.append("%s stock added. Current price is %f\n".formatted(groupYandex.getName(), groupYandex.getStockPrice()));
        bankSber.updatePrice(271.46);
        exchangeLog.append("%s price change. Current price is %f\n".formatted(bankSber.getName(), bankSber.getStockPrice()));

        moscowExchange.add(bankTinkoff);
        exchangeLog.append("%s stock added. Current price is %f\n".formatted(bankTinkoff.getName(), bankTinkoff.getStockPrice()));
        exchangeLog.append("Most valuable stock is %s, its price is %f\n".formatted(moscowExchange.mostValuableStock().getName(), moscowExchange.mostValuableStock().getStockPrice()));

        exchangeLog.append(moscowExchange.closeExchange());
        return exchangeLog.toString();
    }
}
