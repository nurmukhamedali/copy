import Vue from 'vue'
import Vuex from 'vuex'
import productApi from "api/products";
import categoryApi from "api/categories";
import cartItemApi from "api/cartItems";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        products,
        categories,
        profile,
        cart,
        items,
        totalPages: frontendData.totalPages,
        currentPage: frontendData.currentPage
    },
    getters: {
        sortedProducts: state => (state.products || []).sort((a, b) => -(a.id - b.id)),
        sortedCategories: state => (state.categories || []).sort((a, b) => -(a.id - b.id)),
        sortedCart: state => (state.items || []).sort((a, b) => -(a.product.id - b.product.id))
    },
    mutations: {
        // Product
        addProductMutation(state, product){
            state.products = [
                ...state.products,
                product
            ]
        },
        updateProductMutation(state, product){
            const updateIndex = state.products.findIndex(item => item.id === product.id)

            state.products = [
                ...state.products.slice(0, updateIndex),
                product,
                ...state.products.slice(updateIndex + 1)
            ]
        },
        removeProductMutation(state, product){
            const deletionIndex = state.products.findIndex(item => item.id === product.id)

            if (deletionIndex > -1){
                state.products = [
                    ...state.products.slice(0, deletionIndex),
                    ...state.products.slice(deletionIndex + 1)
                ]
            }
        },
        // Category
        addCategoryMutation(state, category){
            state.categories = [
                ...state.categories,
                category
            ]
        },
        updateCategoryMutation(state, category){
            const updateIndex = state.categories.findIndex(item => item.id === category.id)

            state.categories = [
                ...state.categories.slice(0, updateIndex),
                category,
                ...state.categories.slice(updateIndex + 1)
            ]
        },
        removeCategoryMutation(state, category){
            const deletionIndex = state.categories.findIndex(item => item.id === category.id)

            if (deletionIndex > -1){
                state.categories = [
                    ...state.categories.slice(0, deletionIndex),
                    ...state.categories.slice(deletionIndex + 1)
                ]
            }
        },
        // Cart Item
        addCartItemMutation(state, cartItem){
            state.items = [
                ...state.items,
                cartItem
            ]
            state.cart.totalPrice = state.items.reduce((fItem, sItem) => {
                let sPrice = sItem.quantity * sItem.product.price;
                return fItem + sPrice;
            }, 0)
            state.cart.totalItems = state.items.reduce((fItem, sItem) => {
                let sAmount = sItem.quantity;
                return fItem + sAmount;
            }, 0)
        },
        updateCartItemMutation(state, cartItem){
            const updateIndex = state.items.findIndex(item => (item.product.id === cartItem.product.id))

            state.items = [
                ...state.items.slice(0, updateIndex),
                cartItem,
                ...state.items.slice(updateIndex + 1)
            ]
            state.cart.totalPrice = state.items.reduce((fItem, sItem) => {
                let sPrice = sItem.quantity * sItem.product.price;
                return fItem + sPrice;
            }, 0)
            state.cart.totalItems = state.items.reduce((fItem, sItem) => {
                let sAmount = sItem.quantity;
                return fItem + sAmount;
            }, 0)
        },
        removeCartItemMutation(state, cartItem){
            const deletionIndex = state.items.findIndex(item => item.product.id === cartItem.product.id)

            if (deletionIndex > -1){
                state.items = [
                    ...state.items.slice(0, deletionIndex),
                    ...state.items.slice(deletionIndex + 1)
                ]
                state.cart.totalPrice = state.items.reduce((fItem, sItem) => {
                    let sPrice = sItem.quantity * sItem.product.price;
                    return fItem + sPrice;
                }, 0)
                state.cart.totalItems = state.items.reduce((fItem, sItem) => {
                    let sAmount = sItem.quantity;
                    return fItem + sAmount;
                }, 0)
            }
        },
        updateProductPageMutation(state, products){
            state.products = products
        },
        updateTotalPagesMutation(state, totalPages){
            state.totalPages = totalPages;
        },
        updateCurrentPageMutation(state, currentPage){
            state.currentPage = currentPage;
        },

    },
    actions: {
        // Product
        async addProductAction({commit, state}, product){
            const result = await productApi.add(product)
            const data = await result.json()
            const index = state.products.findIndex(item => item.id === data.id);

            if (index > -1){
                commit('updateProductMutation', data)
            } else {
                commit('addProductMutation', data)
            }
        },
        async updateProductAction({commit}, product){
            const result = await productApi.update(product)
            const data = await result.json()
            commit('updateProductMutation', data)
        },
        async removeProductAction({commit}, product){
            const result = await productApi.remove(product.id)
            if (result.ok){
                commit('removeProductMutation', product)
            }
        },
        // Category
        async addCategoryAction({commit, state}, category){
            const result = await categoryApi.add(category)
            const data = await result.json()
            const index = state.categories.findIndex(item => item.id === data.id);

            if (index > -1){
                commit('updateCategoryMutation', data)
            } else {
                commit('addCategoryMutation', data)
            }
        },
        async updateCategoryAction({commit}, category){
            const result = await categoryApi.update(category)
            const data = await result.json()
            commit('updateCategoryMutation', data)
        },
        async removeCategoryAction({commit}, category){
            const result = await categoryApi.remove(category.id)
            if (result.ok){
                commit('removeCategoryMutation', category)
            }
        },
        // Cart Item
        async addCartItemAction({commit, state}, cartItem){
            const result = await cartItemApi.add(cartItem)
            const data = await result.json()
            const index = state.items.findIndex(item => (item.product.id === data.product.id && item.cart.id === data.cart.id));
            if (index > -1){
                commit('updateCartItemMutation', data)
            } else {
                commit('addCartItemMutation', data)
            }
        },
        async updateCartItemAction({commit}, cartItem){
            const result = await cartItemApi.update(cartItem)
            const data = await result.json()
            commit('updateCartItemMutation', data)
        },
        async removeCartItemAction({commit}, cartItem){
            const result = await cartItemApi.remove(cartItem.product.id)
            if (result.ok){
                commit('removeCartItemMutation', cartItem)
            }
        },
        async loadPageAction({commit, state}, page){
            const result = await productApi.page(page)
            const data = await result.json()
            commit('updateProductPageMutation', data.products)
            commit('updateTotalPagesMutation', data.totalPages)
            commit('updateCurrentPageMutation', Math.min(data.currentPage, data.totalPages))
        }
    }
})