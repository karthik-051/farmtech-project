package com.mitron.onlinestore.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.mitron.onlinestore.domain.models.service.ReceiptServiceModel;
import com.mitron.onlinestore.domain.models.view.ReceiptViewModel;
import com.mitron.onlinestore.service.ReceiptService;
import com.mitron.onlinestore.util.error.ReceiptNotFoundException;
import com.mitron.onlinestore.web.annotations.PageTitle;

import static com.mitron.onlinestore.util.constants.AppConstants.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/receipts")
public class ReceiptsController extends BaseController {

    private final ReceiptService receiptService;
    private final ModelMapper modelMapper;

    public ReceiptsController (ReceiptService receiptService,
            ModelMapper modelMapper){
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle(RECEIPTS)
    public ModelAndView getAllReceipts(ModelAndView modelAndView) {

        List<ReceiptViewModel> allReceipts =
                mapReceiptServiceToViewModel(receiptService.findAllReceipts());

        modelAndView.addObject(RECEIPTS_TO_LOWER_CASE, allReceipts);

        return view("receipt/receipts", modelAndView);
    }

    @GetMapping("/all/details/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PageTitle(RECEIPTS_DETAILS)
    public ModelAndView allReceiptDetails(@PathVariable String id, ModelAndView modelAndView) {

        ReceiptViewModel receiptViewModel =
                modelMapper.map(receiptService.findReceiptById(id), ReceiptViewModel.class);

        modelAndView.addObject(RECEIPT_TO_LOWER_CASE, receiptViewModel);

        return super.view("receipt/receipt-details", modelAndView);
    }

    @GetMapping("/my")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView getMyOrders(ModelAndView modelAndView, Principal principal) {

        List<ReceiptViewModel> myReceipts =
                mapReceiptServiceToViewModel(receiptService.findAllReceiptsByUsername(principal.getName()));

        modelAndView.addObject(RECEIPTS_TO_LOWER_CASE, myReceipts);

        return view("receipt/receipts", modelAndView);
    }

    @GetMapping("/my/details/{id}")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(RECEIPTS_DETAILS)
    public ModelAndView myOrderDetails(@PathVariable String id, ModelAndView modelAndView) {

        ReceiptServiceModel receipt = receiptService.findReceiptById(id);

        modelAndView.addObject(RECEIPT_TO_LOWER_CASE, modelMapper.map(receipt, ReceiptViewModel.class));

        return super.view("receipt/receipt-details", modelAndView);
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView createReceipt(String orderId, Principal principal) {

        receiptService.createReceipt(orderId, principal.getName());

        return super.redirect("/receipts/my");
    }

    private List<ReceiptViewModel> mapReceiptServiceToViewModel
            (List<ReceiptServiceModel> receiptServiceModels){
        return receiptServiceModels.stream()
                .map(product -> modelMapper.map(product, ReceiptViewModel.class))
                .collect(Collectors.toList());
    }

    @ExceptionHandler({ReceiptNotFoundException.class})
    public ModelAndView handleProductNotFound(ReceiptNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView(ERROR);
        modelAndView.addObject(MESSAGE, e.getMessage());
        modelAndView.addObject(STATUS_CODE, e.getStatusCode());

        return modelAndView;
    }

}
