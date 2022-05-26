import Vue from 'vue'

const orders = Vue.resource('orders{/id}')

export default {
    add: order => orders.save({}, order),
}