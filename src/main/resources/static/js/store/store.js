import Vue from 'vue'
import Vuex from 'vuex'
import productApi from "api/products";
import categoryApi from "api/categories";
import cartItemApi from "api/cartItems";
import orderApi from "api/orders";
import userApi from "api/users";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        productInfo: {
            products: [],
            totalPages: 0,
            currentPage: 0,
        },
        categoryInfo: {
            categories,
        },
        personalInfo: {
            profile,
            cart,
            orders
        },
        adminInfo: {
            users: [],
            orders: []
        },
        styleInfo: {
            statusColors: {
                created: 'primary',
                in_progress: 'warning',
                declined: 'error',
                delivered: 'success'
            }
        },
        defaultImage: 'https://pinemelon.com/static/platform/frontend/assets/en/banners/store_main.svg',
    },
    getters: {
        sortedProducts: state => (state.productInfo.products || []).sort((a, b) => -(a.id - b.id)),
        sortedCategories: state => (state.categoryInfo.categories || []).sort((a, b) => -(a.id - b.id)),
        sortedCart: state => (state.personalInfo.cart.items || []).sort((a, b) => -(a.product.id - b.product.id))
    },
    mutations: {
        //// Product
        addProductMutation(state, product){
            state.productInfo.products = [
                ...state.productInfo.products,
                product
            ]
},
        updateProductMutation(state, product){
            const updateIndex = state.productInfo.products.findIndex(item => item.id === product.id)

            state.productInfo.products = [
                ...state.productInfo.products.slice(0, updateIndex),
                product,
                ...state.productInfo.products.slice(updateIndex + 1)
            ]
        },
        removeProductMutation(state, product){
            const deletionIndex = state.productInfo.products.findIndex(item => item.id === product.id)

            if (deletionIndex > -1){
                state.productInfo.products = [
                    ...state.productInfo.products.slice(0, deletionIndex),
                    ...state.productInfo.products.slice(deletionIndex + 1)
                ]
            }
        },
        updateProductPageMutation(state, products){
            state.productInfo.products = products
        },
        updateProductTotalPagesMutation(state, totalPages){
            state.productInfo.totalPages = totalPages;
        },
        updateProductCurrentPageMutation(state, currentPage){
            state.productInfo.currentPage = currentPage;
        },
        // Category
        addCategoryMutation(state, category){
            state.categoryInfo.categories = [
                ...state.categoryInfo.categories,
                category
            ]
        },
        updateCategoryMutation(state, category){
            const updateIndex = state.categoryInfo.categories.findIndex(item => item.id === category.id)

            state.categoryInfo.categories = [
                ...state.categoryInfo.categories.slice(0, updateIndex),
                category,
                ...state.categoryInfo.categories.slice(updateIndex + 1)
            ]
        },
        removeCategoryMutation(state, category){
            const deletionIndex = state.categoryInfo.categories.findIndex(item => item.id === category.id)

            if (deletionIndex > -1){
                state.categoryInfo.categories = [
                    ...state.categoryInfo.categories.slice(0, deletionIndex),
                    ...state.categoryInfo.categories.slice(deletionIndex + 1)
                ]
            }
        },
        // Cart Item
        addCartItemMutation(state, cartItem){
            state.personalInfo.cart.items = [
                ...state.personalInfo.cart.items,
                cartItem
            ]
            state.personalInfo.cart.totalPrice = state.personalInfo.cart.items.reduce((fItem, sItem) => {
                let sPrice = sItem.quantity * sItem.product.price;
                return fItem + sPrice;
            }, 0)
            state.personalInfo.cart.totalItems = state.personalInfo.cart.items.reduce((fItem, sItem) => {
                let sAmount = sItem.quantity;
                return fItem + sAmount;
            }, 0)
        },
        updateCartItemMutation(state, cartItem){
            const updateIndex = state.personalInfo.cart.items.findIndex(item => (item.product.id === cartItem.product.id))

            state.personalInfo.cart.items = [
                ...state.personalInfo.cart.items.slice(0, updateIndex),
                cartItem,
                ...state.personalInfo.cart.items.slice(updateIndex + 1)
            ]
            state.personalInfo.cart.totalPrice = state.personalInfo.cart.items.reduce((fItem, sItem) => {
                let sPrice = sItem.quantity * sItem.product.price;
                return fItem + sPrice;
            }, 0)
            state.personalInfo.cart.totalItems = state.personalInfo.cart.items.reduce((fItem, sItem) => {
                let sAmount = sItem.quantity;
                return fItem + sAmount;
            }, 0)
        },
        removeCartItemMutation(state, cartItem){
            const deletionIndex = state.personalInfo.cart.items.findIndex(item => item.product.id === cartItem.product.id)

            if (deletionIndex > -1){
                state.personalInfo.cart.items = [
                    ...state.personalInfo.cart.items.slice(0, deletionIndex),
                    ...state.personalInfo.cart.items.slice(deletionIndex + 1)
                ]
                state.personalInfo.cart.totalPrice = state.personalInfo.cart.items.reduce((fItem, sItem) => {
                    let sPrice = sItem.quantity * sItem.product.price;
                    return fItem + sPrice;
                }, 0)
                state.personalInfo.cart.totalItems = state.personalInfo.cart.items.reduce((fItem, sItem) => {
                    let sAmount = sItem.quantity;
                    return fItem + sAmount;
                }, 0)
            }
        },
        updateCartMutation(state, cart){
            state.productInfo.cart = cart
        },
        updateProfileMutation(state, profile){
            state.personalInfo.profile = profile
        },

        // Order
        addOrderMutation(state, order){
            order.items = []
            state.personalInfo.cart.items.forEach(item => {
                order.items.push({
                        product: item.product,
                        quantity: item.quantity
                    })
            })
            order.totalItems = state.personalInfo.cart.totalItems
            order.totalPrice = state.personalInfo.cart.totalPrice

            // clearCart
            state.personalInfo.cart.totalItems = 0;
            state.personalInfo.cart.totalPrice = 0;
            state.personalInfo.cart.items = []

            state.personalInfo.orders = [
                ...state.personalInfo.orders,
                order
            ]
        },
        updateOrderMutation(state, order){
            const updateIndex = state.personalInfo.orders.findIndex(item => item.id === order.id)

            order.items = []
            state.personalInfo.cart.items.forEach(item => {
                order.items.push({
                    product: item.product,
                    quantity: item.quantity
                })
            })

            state.personalInfo.orders = [
                ...state.personalInfo.orders.slice(0, updateIndex),
                order,
                ...state.personalInfo.orders.slice(updateIndex + 1)
            ]
        },
        updateOrdersMutation(state, orders){
            state.personalInfo.orders = orders
        },
        updateOrdersPageMutation(state, orders){
            state.adminInfo.orders = orders
        },

        // User
        updateUsersPageMutation(state, users){
            state.adminInfo.users = users
        },
        updateUserMutation(state, user){
            const updateIndex = state.adminInfo.users.findIndex(item => item.id === user.id)

            state.adminInfo.users = [
                ...state.adminInfo.users.slice(0, updateIndex),
                user,
                ...state.adminInfo.users.slice(updateIndex + 1)
            ]
        },

    },
    actions: {
        // Product
        async addProductAction({commit, state}, product){
            const result = await productApi.add(product)
            const data = await result.json()
            const index = state.productInfo.products.findIndex(item => item.id === data.id);

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
        async loadPageAction({commit, state}, params){
            const result = await productApi.page(params.page, params.categoryId, params.minPrice, params.maxPrice)
            const data = await result.json()
            commit('updateProductPageMutation', data.products)
            commit('updateProductTotalPagesMutation', data.totalPages)
            commit('updateProductCurrentPageMutation', Math.min(data.currentPage, data.totalPages))
        },
        // Category
        async addCategoryAction({commit, state}, category){
            const result = await categoryApi.add(category)
            const data = await result.json()
            const index = state.categoryInfo.categories.findIndex(item => item.id === data.id);

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
        // Cart & Items
        async addCartItemAction({commit, state}, cartItem){
            const result = await cartItemApi.add(cartItem)
            const data = await result.json()
            const index = state.personalInfo.cart.items.findIndex(item => (item.product.id === data.product.id));
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
        async loadCartAction({commit, state}){
            const result = await cartApi.getByUser()
            const data = await result.json()
            commit('updateCartMutation', data)
            // commit('updateProductTotalPagesMutation', data.totalPages)
            // commit('updateProductCurrentPageMutation', Math.min(data.currentPage, data.totalPages))
        },

        // Order
        async addOrderAction({commit, state}, order){
            const result = await orderApi.add(order)
            const data = await result.json()

            const index = state.personalInfo.orders.findIndex(item => (item.id === data.id))

            if (index > -1){
                commit('updateOrderMutation', data)
            } else {
                commit('addOrderMutation', data)
            }

        },
        async loadOrdersAction({commit, state}){
            const result = await orderApi.getByUser()
            const data = await result.json()
            commit('updateOrdersMutation', data)
            // commit('updateProductTotalPagesMutation', data.totalPages)
            // commit('updateProductCurrentPageMutation', Math.min(data.currentPage, data.totalPages))
        },
        // Profile
        async loadProfileAction({commit, state}){
            const result = await userApi.get()
            const data = await result.json()
            commit('updateProfileMutation', data)
            // commit('updateProductTotalPagesMutation', data.totalPages)
            // commit('updateProductCurrentPageMutation', Math.min(data.currentPage, data.totalPages))
        },

        // Admin Page
        async loadOrdersPageAction({commit, state}){
            const result = await orderApi.getAll()
            const data = await result.json()
            commit('updateOrdersPageMutation', data)
            // commit('updateProductTotalPagesMutation', data.totalPages)
            // commit('updateProductCurrentPageMutation', Math.min(data.currentPage, data.totalPages))
        },

        async loadUsersPageAction({commit, state}){
            const result = await userApi.get()
            const data = await result.json()
            commit('updateUsersPageMutation', data)
            // commit('updateProductTotalPagesMutation', data.totalPages)
            // commit('updateProductCurrentPageMutation', Math.min(data.currentPage, data.totalPages))
        },

        async updateOrderStatusAction({commit, state}, order){
            const result = await orderApi.update(order)
            const data = await result.json()

            const index = state.personalInfo.orders.findIndex(item => (item.id === data.id))

            if (index > -1){
                commit('updateOrderMutation', data)
            } else {
                commit('addOrderMutation', data)
            }
        },

        async updateUserAction({commit}, user){
            const result = await userApi.update(user)
            const data = await result.json()
            commit('updateUserMutation', data)
        },
    }
})