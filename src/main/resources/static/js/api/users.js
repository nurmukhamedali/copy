import Vue from 'vue'

const users = Vue.resource('admin/customers{/id}')

export default {
    get: () => users.get(),
    update: user => users.update({id: user.id}, user),
}