<template>
  <v-card color="grey lighten-4" v-if="user">
    <v-container>
      <v-layout align-center justify-space-around row>
        <v-avatar class="ma-2" size="88">
          <v-img
              :src="user.avatar"
              contain
          />
        </v-avatar>
        <v-card-text class="subheading">
          <span>ID: {{user.id}}</span>
          <h3>Cart ID: {{user.cart.id}}</h3>
        </v-card-text>
      </v-layout>

    </v-container>
    <v-container column>
    <v-text-field class="ma-2"
                  label="Name"
                  placeholder="Enter user name"
                  v-model="user.name"
    />
    <v-text-field class="ma-2"
                  label="Username"
                  placeholder="Enter user username"
                  v-model="user.username"
    />
    <v-text-field class="ma-2"
                  label="Address"
                  placeholder="Enter user address"
                  v-model="user.address"
    />

      <v-card-actions>
        <v-btn small color="success" @click="updateUser" class="">
          Save
        </v-btn>
      </v-card-actions>
    </v-container>
  </v-card>
</template>

<script>
import {mapActions, mapGetters, mapState,} from 'vuex'

export default {
  name: "DevUserDetailsCard",
  components: {
  },
  props: ['user'],
  data() {
    return {
      item: null
    }
  },
  computed: {
  },
  methods: {
    ...mapActions(['updateUserAction']),
    updateUser(){
      this.updateUserAction(this.user)
    }
  }
}
</script>

<style scoped>

</style>