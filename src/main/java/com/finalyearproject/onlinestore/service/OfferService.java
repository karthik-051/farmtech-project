package com.mitron.onlinestore.service;

import java.util.List;

import com.mitron.onlinestore.domain.models.service.OfferServiceModel;

public interface OfferService {

    List<OfferServiceModel> findAllOffers();
}
