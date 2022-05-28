<template>
  <v-container color="grey lighten-4">
    <v-card-title >
      <h3>Users
        <span>({{ adminInfo.users.length }} users)</span>
      </h3>
    </v-card-title>
    <v-layout justify-space-around row>
      <v-flex>
        <v-layout align-space-around justify-start column reverse>
          <dev-user-card v-for="user in adminInfo.users" :key="user.id" :user="user"  :showUser="showUser"></dev-user-card>
        </v-layout>
      </v-flex>
      <v-flex sm6 md4>
        <dev-user-details-card :user="user"/>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import {mapActions, mapGetters, mapState,} from 'vuex'
import DevUserDetailsCard from "components/admin/users/DevUserDetailsCard.vue";
import DevUserCard from "components/admin/users/DevUserCard.vue";

export default {
  name: "UserList",
  components: {
    DevUserDetailsCard,
    DevUserCard,
  },
  data() {
    return {
      user: null,
    }
  },
  computed: {
    ...mapState(['adminInfo']),
  },
  created() {
    this.load()
  },
  methods: {
    ...mapActions(['loadUsersPageAction']),
    showUser(user){
      this.user = user;
    },
    load(){
      this.loadUsersPageAction();
    }
  }
}
</script>

<style scoped>

</style>