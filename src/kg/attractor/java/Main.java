package kg.attractor.java;

// import static java.util.stream.Collectors.*;
// import static java.util.Comparator.*;

// используя статические imports
// мы импортируем *всё* из Collectors и Comparator.
// теперь нам доступны такие операции как
// toList(), toSet() и прочие, без указания уточняющего слова Collectors. или Comparator.
// вот так было до импорта Collectors.toList(), теперь стало просто toList()


import kg.attractor.java.homework.RestaurantOrders;
import kg.attractor.java.homework.domain.Order;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        // это для домашки
        // выберите любое количество заказов, какое вам нравится.
        var orders=RestaurantOrders.read("orders_100.json").getOrders();
        //var orders = RestaurantOrders.read("orders_100.json").getOrders();
        //var orders = RestaurantOrders.read("orders_1000.json").getOrders();
        //var orders = RestaurantOrders.read("orders_10_000.json").getOrders();

        // протестировать ваши методы вы можете как раз в этом файле (или в любом другом, в котором вам будет удобно)
        //System.out.println(orders.get(5).getTotal());
        //RestaurantOrders.read("orders_100.json").printAllOrders();
        //RestaurantOrders.read("orders_100.json").highestTotalPrice(5);
        //RestaurantOrders.read("orders_100.json").lowestTotalPrice(5).stream().forEach(System.out::println);
        //RestaurantOrders.read("orders_100.json").getAllHomeDelivery().forEach(System.out::println);
        //RestaurantOrders.read("orders_100.json").printMinAndMax();
        //RestaurantOrders.read("orders_100.json").listOfRange(7, 50).stream().forEach(System.out::println);
        //System.out.println(RestaurantOrders.read("orders_100.json").sumAllOrders());
        //RestaurantOrders.read("orders_100.json").getUniqSortEmails().stream().forEach(System.out::println);
//============================================================================================================

        for (Map.Entry<String, List<Order>> entry : RestaurantOrders.read("orders_100.json").getOrdersGroupByCustomer().entrySet()) {
           System.out.println(entry);
       }

            System.out.println(RestaurantOrders.read("orders_100.json").getCustomerAndFullAmount());



            //----------------------------
        }



    }
