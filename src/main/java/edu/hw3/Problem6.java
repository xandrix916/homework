package edu.hw3;

import java.util.PriorityQueue;
import org.jetbrains.annotations.NotNull;


public class Problem6 {

    private static final double SBER_PRICE = 271.54;
    private static final double VTBR_PRICE = 0.02609;
    private static final double TCSG_PRICE = 3490.5;
    private static final double YNDX_PRICE = 2674.4;
    private static final double GAZP_PRICE = 170.51;
    private static final double SBER_PRICE_NEW = 271.46;
    public static final String STOCK_ADDED = "%s stock added. Current price is %f\n";
    public static final String MVS_MESSAGE = "Most valuable stock is %s, its price is %f\n";


    public String exchangeExample() {
        StringBuilder exchangeLog = new StringBuilder();

        Stock bankSber = new Stock("SBER", SBER_PRICE);
        Stock bankVTB = new Stock("VTBR", VTBR_PRICE);
        Stock bankTinkoff = new Stock("TCSG", TCSG_PRICE);
        Stock groupYandex = new Stock("YNDX", YNDX_PRICE);
        Stock groupGazprom = new Stock("GAZP", GAZP_PRICE);

        MoscowExchange moscowExchange = new MoscowExchange();
        moscowExchange.add(groupGazprom);
        exchangeLog.append(STOCK_ADDED.formatted(groupGazprom.getName(),
            groupGazprom.getStockPrice()));

        moscowExchange.add(bankVTB);
        exchangeLog.append(STOCK_ADDED.formatted(bankVTB.getName(),
            bankVTB.getStockPrice()));
        exchangeLog.append(MVS_MESSAGE.formatted(moscowExchange.mostValuableStock().getName(),
            moscowExchange.mostValuableStock().getStockPrice()));

        moscowExchange.add(bankSber);
        exchangeLog.append(STOCK_ADDED.formatted(bankSber.getName(), bankSber.getStockPrice()));
        exchangeLog.append(MVS_MESSAGE.formatted(moscowExchange.mostValuableStock().getName(),
            moscowExchange.mostValuableStock().getStockPrice()));

        moscowExchange.add(groupYandex);
        exchangeLog.append(STOCK_ADDED.formatted(groupYandex.getName(), groupYandex.getStockPrice()));
        bankSber.updatePrice(SBER_PRICE_NEW);
        exchangeLog.append("%s price change. Current price is %f\n".formatted(bankSber.getName(),
            bankSber.getStockPrice()));

        moscowExchange.add(bankTinkoff);
        exchangeLog.append(STOCK_ADDED.formatted(bankTinkoff.getName(), bankTinkoff.getStockPrice()));
        exchangeLog.append(MVS_MESSAGE.formatted(moscowExchange.mostValuableStock().getName(),
            moscowExchange.mostValuableStock().getStockPrice()));

        exchangeLog.append(moscowExchange.closeExchange());
        return exchangeLog.toString();
    }

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
                return (int) -delta;
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

        private final PriorityQueue<Stock> stockQueue = new PriorityQueue<>();
    }
}
