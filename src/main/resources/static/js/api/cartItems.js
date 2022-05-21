import Vue from 'vue'

const cartItems = Vue.resource('cartItem{/id}')

export default {
    add: item => cartItems.save({}, item),
    remove: id => cartItems.remove({id}),
}