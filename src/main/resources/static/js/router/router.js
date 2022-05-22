import Vue from 'vue'
import VueRouter from "vue-router"
import Auth from "pages/Auth.vue";
import Profile from "pages/Profile.vue";
import ProductList from "pages/ProductList.vue";
import DevProductList from "components/admin/product/DevProductList.vue";
import DevCategoryList from "components/admin/category/DevCategoryList.vue";
import CartItemList from "components/cart/CartItemList.vue";
import AdminPage from "components/admin/AdminPage.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/products', component: ProductList },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '/cart', component: CartItemList},
    { path: '*', component: ProductList },

    { path: '/dev', component: AdminPage},
    { path: '/dev-products', component: DevProductList },
    { path: '/dev-categories', component: DevCategoryList },

]

export default new VueRouter({
    mode: 'history',
    routes // routes: routes
})
