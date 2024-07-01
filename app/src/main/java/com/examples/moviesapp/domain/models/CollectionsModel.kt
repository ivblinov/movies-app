package com.examples.moviesapp.domain.models

import com.examples.moviesapp.entities.Collections

data class CollectionsModel(
    override val total: Int,
    override val totalPages: Int,
    override val items: List<CollectionItemModel>
) : Collections