package com.mitron.onlinestore.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mitron.onlinestore.domain.entities.enumeration.Status;
import com.mitron.onlinestore.domain.models.service.OrderProductServiceModel;
import com.mitron.onlinestore.domain.models.service.OrderServiceModel;
import com.mitron.onlinestore.domain.models.view.*;
import com.mitron.onlinestore.service.OrderService;
import com.mitron.onlinestore.util.error.OrderNotFoundException;
import com.mitron.onlinestore.web.annotations.PageTitle;

import static com.mitron.onlinestore.util.constants.AppConstants.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrdersController extends BaseController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;

    public OrdersController(OrderService orderService, ModelMapper modelMapper){
        this.orderService = orderService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle(ORDERS)
    public ModelAndView getAllOrders(ModelAndView modelAndView) {

        List<OrderViewModel> viewModels = mapListOrderServiceToViewModel(orderService.findAllOrders());

        modelAndView.addObject(ORDERS_TO_LOWER_CASE, viewModels);

        return view("order/all-orders", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView allOrderDetails(@PathVariable String id, ModelAndView modelAndView) {

        OrderDetailsViewModel order = loadOrderDetailsViewModel(id);

        modelAndView.addObject(ORDER_TO_LOWER_CASE, order);

        return view("order/order-products", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(MY_ORDERS)
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {
        String customerName = principal.getName();

        List<OrderViewModel> myOrders =
                mapListOrderServiceToViewModel(orderService.findOrdersByCustomer(customerName));

        List<MyOrderViewModel> myPendingOrders =
                mapListOrderServiceToMyViewModel(orderService.findOrdersByCustomerAndStatus(customerName, Status.Pending));

        List<MyOrderViewModel> myShippedOrders =
                mapListOrderServiceToMyViewModel(orderService.findOrdersByCustomerAndStatus(customerName, Status.Shipped));

        List<MyOrderViewModel> myDeliveredOrders =
                mapListOrderServiceToMyViewModel(orderService.findOrdersByCustomerAndStatus(customerName, Status.Delivered));

        modelAndView.addObject(ORDERS_TO_LOWER_CASE, myOrders);
        modelAndView.addObject(MY_PENDING_ORDERS, myPendingOrders);
        modelAndView.addObject(MY_SHIPPED_ORDERS, myShippedOrders);
        modelAndView.addObject(MY_DELIVERED_ORDERS, myDeliveredOrders);

        return view("order/my-orders", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(ORDER_DETAILS)
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {

        OrderDetailsViewModel order = loadOrderDetailsViewModel(id);

        modelAndView.addObject(ORDER_TO_LOWER_CASE, order);

        return view("order/order-details", modelAndView);
    }

    @GetMapping("/change/status/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView orderChangeStatus(@PathVariable String id) {

        orderService.changeOrderStatus(id);

        return redirect("/order/all");
    }


    @GetMapping("/fetch/{status}")
    @ResponseBody
    public List<OrderViewModel> fetchByCategory(@PathVariable String status) {

        return loadOrdersByStatus(status);

    }

    private List<OrderViewModel> loadOrdersByStatus(String status) {

        Status statusStatus = Status.Pending;

        switch (status){
            case STATUS_ALL:
                return mapListOrderServiceToViewModel(orderService.findAllOrders());
            case STATUS_SHIPPED:
                statusStatus = Status.Shipped;
                break;
            case STATUS_DELIVERED:
                statusStatus = Status.Delivered;
                break;
            case STATUS_ACQUIRED:
                statusStatus = Status.Acquired;
                break;
        }

        return mapListOrderServiceToViewModel(orderService.findOrdersByStatus(statusStatus));
    }

    private OrderDetailsViewModel loadOrderDetailsViewModel(String id) {
        OrderServiceModel orderServiceModel = orderService.findOrderById(id);
        List<OrderProductServiceModel> products = orderServiceModel.getProducts();

        OrderDetailsViewModel order = modelMapper.map(orderServiceModel, OrderDetailsViewModel.class);
        List<ShoppingCartItem> items = new ArrayList<>();

        Map<OrderProductServiceModel, Integer> productItems = new HashMap<>();

        for (OrderProductServiceModel product: products) {
            productItems.putIfAbsent(product, ZERO_NUMBER);
            int quantity = productItems.get(product) + ONE_NUMBER;
            productItems.put(product, quantity);
        }

        for (Map.Entry<OrderProductServiceModel, Integer> productKVP : productItems.entrySet()) {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();

            shoppingCartItem.setQuantity(productKVP.getValue());
            OrderProductViewModel orderProductViewModel =
                    modelMapper.map(productKVP.getKey(), OrderProductViewModel.class);
            shoppingCartItem.setProduct(orderProductViewModel);

            items.add(shoppingCartItem);
        }
        order.setItems(items);

        return order;
    }

    private List<OrderViewModel> mapListOrderServiceToViewModel(List<OrderServiceModel> orderServiceModel){
        return orderServiceModel.stream()
                .map(order -> modelMapper.map(order, OrderViewModel.class))
                .collect(Collectors.toList());
    }

    private List<MyOrderViewModel> mapListOrderServiceToMyViewModel(List<OrderServiceModel> orderServiceModel){
        return orderServiceModel.stream()
                .map(order -> modelMapper.map(order, MyOrderViewModel.class))
                .collect(Collectors.toList());
    }

    @ExceptionHandler({OrderNotFoundException.class})
    public ModelAndView handleProductNotFound(OrderNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(MESSAGE, e.getMessage());
        modelAndView.addObject(STATUS_CODE, e.getStatusCode());

        return modelAndView;
    }
    @PostMapping("/accept/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView acceptOrder(@PathVariable String id) {
        orderService.acceptOrder(id);
        return redirect("/order/all");
    }

    @PostMapping("/ship/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView shipOrder(@PathVariable String id) {
        orderService.shipOrder(id);
        return redirect("/order/all");
    }


}
