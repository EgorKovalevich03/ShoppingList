package com.example.shoppinglist.data

import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository{

    //переменная которая хранит коллекцию элементов
    private val shopList = mutableListOf<ShopItem>()

    //счётчик id
    private var autoIncrementId = 0

    //добавление айтема в список
    override fun addShopItem(shopItem: ShopItem) {
        if(shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrementId++
        }
        shopList.add(shopItem)
    }

    //удаление айтема из списка
    override fun deleteShopItem(shopItem: ShopItem) {
        shopList.remove(shopItem)
    }

    //редактирование элемента
    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

    //получиние айтема из списка
    override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find{
            it.id == shopItemId}
            ?: throw RuntimeException("Element with id:$shopItemId not found")
    }

    //получение всего списка
    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

}