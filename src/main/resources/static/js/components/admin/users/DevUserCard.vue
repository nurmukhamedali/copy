<template>
  <v-card flat class="ma-1 v-btn--round" @click="show">
    <v-card-text class="py-2 px-0">
      <v-layout align-center justify-space-around>
        <small class="text-truncate"># {{ user.id }}</small>
        <small>{{ user.username }}</small>
        <v-btn flat round small icon color="success" class="ma-0" @click="show">
          <v-icon>start</v-icon>
        </v-btn>
      </v-layout>
    </v-card-text>
  </v-card>
</template>

<script>
import { mapActions, mapState } from 'vuex'
export default {
  name: "DevUserCard",
  props: ['user', 'showUser'],
  methods: {
    show(){
      this.showUser(this.user)
    }
  }
}
</script>

<style scoped>

</style>