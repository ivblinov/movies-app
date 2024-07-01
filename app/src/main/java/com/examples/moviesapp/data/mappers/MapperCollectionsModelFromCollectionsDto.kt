package com.examples.moviesapp.data.mappers

import com.examples.moviesapp.data.mappers.utils.MapperCountryModelList
import com.examples.moviesapp.data.mappers.utils.MapperGenreModel
import com.examples.moviesapp.data.models_dto.CollectionItemDto
import com.examples.moviesapp.data.models_dto.CollectionsDto
import com.examples.moviesapp.domain.models.CollectionItemModel
import com.examples.moviesapp.domain.models.CollectionsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapperCollectionsModelFromCollectionsDto @Inject constructor(
    private val countryModelList: MapperCountryModelList,
    private val genreModelList: MapperGenreModel
) {
    fun transform(collectionsDto: CollectionsDto): CollectionsModel {
        return CollectionsModel(
            total = collectionsDto.total,
            items = getCollectionItemListFromCollectionItemDtoList(collectionsDto.items),
            totalPages = collectionsDto.totalPages
        )
    }

    private fun getCollectionItemListFromCollectionItemDtoList(
        collectionItemDtoList: List<CollectionItemDto>
    ): List<CollectionItemModel> {
        val collectionItemModelList = mutableListOf<CollectionItemModel>()
        collectionItemDtoList.forEach { collectionItemDto ->
            val collectionItemModel = CollectionItemModel(
                kinopoiskId = collectionItemDto.kinopoiskId,
                nameRu = collectionItemDto.nameRu,
                nameEn = collectionItemDto.nameEn,
                nameOriginal = collectionItemDto.nameOriginal,
                countries = countryModelList.getCountryModelListFromCountryDtoList(collectionItemDto.countries),
                genres = genreModelList.getGenreModelListFromGenreDtoList(collectionItemDto.genres),
                ratingKinopoisk = collectionItemDto.ratingKinopoisk,
                ratingImbd = collectionItemDto.ratingImbd,
                year = collectionItemDto.year,
                type = collectionItemDto.type,
                posterUrl = collectionItemDto.posterUrl,
                posterUrlPreview = collectionItemDto.posterUrlPreview
            )
            collectionItemModelList.add(collectionItemModel)
        }
        return collectionItemModelList.toList()
    }
}