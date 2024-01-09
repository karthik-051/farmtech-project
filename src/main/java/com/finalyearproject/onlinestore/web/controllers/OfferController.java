package com.mitron.onlinestore.web.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mitron.onlinestore.domain.models.service.OfferServiceModel;
import com.mitron.onlinestore.domain.models.view.OfferViewModel;
import com.mitron.onlinestore.service.OfferService;
import com.mitron.onlinestore.web.annotations.PageTitle;

import static com.mitron.onlinestore.util.constants.AppConstants.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OfferController extends BaseController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/sales")
    @PreAuthorize("isAuthenticated()")
    @PageTitle(SALES)
    public ModelAndView topOffers(ModelAndView modelAndView) {

        return view("offer/sales", modelAndView);
    }

    @GetMapping("/fetch/sales/{category}")
    @ResponseBody
    public List<OfferViewModel> fetchByCategory(@PathVariable String category) {

        List<OfferViewModel> offerViewModels = mapOfferServiceToViewModel(this.offerService.findAllOffers());

        return offerViewModels;
    }

    private List<OfferViewModel> mapOfferServiceToViewModel(List<OfferServiceModel> offerServiceModel){
        return offerServiceModel.stream()
                .map(product -> modelMapper.map(product, OfferViewModel.class))
                .collect(Collectors.toList());
    }
}
