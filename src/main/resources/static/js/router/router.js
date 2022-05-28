import Vue from 'vue'
import VueRouter from "vue-router"
import Auth from "pages/Auth.vue";
import Profile from "pages/Profile.vue";
import MainPage from "pages/MainPage.vue";
import DevProductList from "components/admin/product/DevProductList.vue";
import DevCategoryList from "components/admin/category/DevCategoryList.vue";
import AdminPage from "components/admin/AdminPage.vue";
import OrderPlace from "components/order/OrderPlace.vue";
import DevOrderList from "components/admin/order/DevOrderList.vue";
import DevUserList from "components/admin/users/DevUserList.vue";
import store from "store/store";
import NotPermitted from "pages/NotPermitted.vue";

Vue.use(VueRouter)

const routes = [
    { path: '/products', component: MainPage },
    { path: '/auth', component: Auth },
    { path: '/profile', component: Profile },
    { path: '/checkout', component: OrderPlace},
    { path: '/invalid', name: "NotPermitted", component: NotPermitted},
    { path: '*', component: MainPage },

    { path: '/dev', component: AdminPage, beforeEnter: checkAdminRights},
    { path: '/dev-products', component: DevProductList , beforeEnter: checkAdminRights},
    { path: '/dev-categories', component: DevCategoryList , beforeEnter: checkAdminRights},
    { path: '/dev-orders', component: DevOrderList , beforeEnter: checkAdminRights},
    { path: '/dev-users', component: DevUserList, beforeEnter: checkAdminRights},

]
function checkAdminRights(to, from, next){
    const roles = store.state.personalInfo.profile.roles
    const isAdmin = roles.indexOf("ADMIN")
    if(isAdmin > -1) {
        next();
    } else {
        next({ path: '/invalid'});
    }
}

const router = new VueRouter({
    mode: 'history',
    routes // routes: routes
})
export default router
