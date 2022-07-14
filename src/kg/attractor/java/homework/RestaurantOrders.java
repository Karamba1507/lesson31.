package kg.attractor.java.homework;

import com.google.gson.Gson;
import kg.attractor.java.homework.domain.Item;
import kg.attractor.java.homework.domain.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class RestaurantOrders {
    // Этот блок кода менять нельзя! НАЧАЛО!
    private List<Order> orders;

    private RestaurantOrders(String fileName) {
        var filePath=Path.of("data", fileName);
        Gson gson=new Gson();
        try {
            orders=List.of(gson.fromJson(Files.readString(filePath), Order[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RestaurantOrders read(String fileName) {
        var ro=new RestaurantOrders(fileName);
        ro.getOrders().forEach(Order::calculateTotal);
        return ro;
    }

    public List<Order> getOrders() {
        return orders;
    }
    // Этот блок кода менять нельзя! КОНЕЦ!

    //----------------------------------------------------------------------
    //------   Реализация ваших методов должна быть ниже этой линии   ------
    //----------------------------------------------------------------------

    // Наполните этот класс решением домашнего задания.
    // Вам необходимо создать все необходимые методы
    // для решения заданий из домашки :)
    // вы можете добавлять все необходимые imports
    //
    public void printAllOrders() {
        orders.stream().forEach(System.out::println);
    }

    public List<Order> highestTotalPrice(long num) {
        return orders.stream().sorted((Comparator.comparingDouble(Order::getTotal).reversed())).limit(num).
                collect(Collectors.toList());
    }

    public List<Order> lowestTotalPrice(long num) {
        return orders.stream().sorted(Comparator.comparingDouble(Order::getTotal)).limit(num).collect(Collectors.toList());
    }

    public List<Order> getAllHomeDelivery() {
        return orders.stream().filter(o -> o.isHomeDelivery()).collect(Collectors.toList());
    }

    public void printMinAndMax() {
        Optional<Order> min=orders.stream().filter(o -> o.isHomeDelivery()).min(Comparator.comparingDouble(Order::getTotal));
        Optional<Order> max=orders.stream().filter(o -> o.isHomeDelivery()).max(Comparator.comparingDouble(Order::getTotal));
        System.out.println("Minimal Order: ");
        System.out.println(min);
        System.out.println();
        System.out.println("Maximal Order:");
        System.out.println(max);
    }

    public List<Order> listOfRange(double minOrderTotal, double maxOrderTotal) {
        return orders.stream().filter(o -> o.getTotal() > minOrderTotal || o.getTotal() < maxOrderTotal).collect(Collectors.toList());
    }

    public double sumAllOrders() {
        return orders.stream().mapToDouble(o -> o.getTotal()).sum();
    }

    public TreeSet<String> getUniqSortEmails() {
        return orders.stream().map(o -> o.getCustomer().getEmail()).collect(Collectors.toCollection(TreeSet::new));
    }

    //============================================================================================================

    public Map<String, List<Order>> getOrdersGroupByCustomer() {
        Map<String, List<Order>> result = orders.stream().collect(Collectors.groupingBy(o -> o.getCustomer().getFullName()));
        return result;
    }


    public Map<String, Double> getCustomerAndFullAmount() {
        return orders.stream().collect(Collectors.groupingBy(o -> o.getCustomer().getFullName(), Collectors.summingDouble(Order::getTotal)));
    }


    public Optional<String> maxSumCastomer() {

        String customer = getCustomerAndFullAmount().entrySet().iterator().next().getKey();
        Double max = getCustomerAndFullAmount().entrySet().iterator().next().getValue();

        for (Map.Entry<String, Double> entry : getCustomerAndFullAmount().entrySet()) {
            if (entry.getValue() > max) {
                customer = entry.getKey();
                max = entry.getValue();
            }
        }
        //return customer;

        return getCustomerAndFullAmount().entrySet().stream().max(Comparator.comparingDouble(Map.Entry::getValue)).map(Map.Entry::getKey);

    }

    public Optional<String> minSumCustomer() {
        return getCustomerAndFullAmount().entrySet().stream().min(Comparator.comparingDouble(Map.Entry::getValue)).map(Map.Entry::getKey);
    }

//    //item by count
//    public void getItemsCount() {
//
//        Map<Item, Long> items = orders.stream().collect(Collectors.groupingBy(Order::getItems.stream(), Collectors.counting());
//
//    }

}
