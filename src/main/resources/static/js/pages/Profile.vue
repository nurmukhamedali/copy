<template>
  <v-container>
    <v-layout align-start justify-center row wrap>
      <v-flex class="pa-2" d-flex xs12 sm3 md3>
        <v-img :src="personalInfo.profile.avatar" round></v-img>
      </v-flex>
      <v-flex class="pa-2" d-flex xs12 sm4 md4>
        <v-layout align-start justify-space-around column>
          <v-flex>{{ personalInfo.profile.name }}</v-flex>
          <v-flex>{{ personalInfo.profile.username }}</v-flex>
          <v-flex>{{ personalInfo.profile.address }}</v-flex>
        </v-layout>
      </v-flex>
      <v-btn v-if="personalInfo.profile" icon href="/logout">
        <v-icon>logout</v-icon>
      </v-btn>
    </v-layout>
    <v-layout>
      <order-list></order-list>
    </v-layout>
  </v-container>
</template>

<script>
import {mapActions, mapState} from "vuex";
import OrderList from "components/order/OrderList.vue";

export default {
  name: "Profile",
  components: {
    OrderList
  },
  computed: mapState(['personalInfo']),
  created() {
    this.loadOrdersAction()
  },
  methods: mapActions(['loadOrdersAction'])
}
</script>

<style scoped>

</style>