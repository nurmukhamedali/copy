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
        cart
    },
    getters: {
        sortedProducts: state => (state.products || []).sort((a, b) => -(a.id - b.id)),
        sortedCategories: state => (state.categories || []).sort((a, b) => -(a.id - b.id)),
        sortedCart: state => (state.cart || []).sort((a, b) => -(a.id - b.id))
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
            state.cart = [
                ...state.cart,
                cartItem
            ]
        },
        updateCartItemMutation(state, cartItem){
            const updateIndex = state.cart.findIndex(item => (item.product.id === cartItem.product.id && item.user.id === cartItem.user.id))

            state.cart = [
                ...state.cart.slice(0, updateIndex),
                cartItem,
                ...state.cart.slice(updateIndex + 1)
            ]
        },
        removeCartItemMutation(state, cartItem){
            const deletionIndex = state.categories.findIndex(item => item.id === cartItem.id)

            if (deletionIndex > -1){
                state.cart = [
                    ...state.cart.slice(0, deletionIndex),
                    ...state.cart.slice(deletionIndex + 1)
                ]
            }
        }
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
            const index = state.cart.findIndex(item => (item.product.id === data.product.id && item.product.id === data.product.id));
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
    }
})