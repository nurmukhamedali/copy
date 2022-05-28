import Vue from 'vue'

const orders = Vue.resource('orders{/id}')
const adminOrders = Vue.resource('admin/orders{/id}')

export default {
    add: order => orders.save({}, order),
    update: order => adminOrders.update({id: order.id}, order),
    getByUser: () => orders.get(),
    getAll: () => adminOrders.get(),
}