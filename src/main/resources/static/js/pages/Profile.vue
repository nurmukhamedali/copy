<template>
  <v-container>
    <v-layout align-start justify-center row wrap>
      <v-flex class="pa-2" d-flex xs12 sm3 md3>
        <v-img :src="profile.avatar" round></v-img>
      </v-flex>
      <v-flex class="pa-2" d-flex xs12 sm4 md4>
        <v-layout align-start justify-space-around column>
          <v-flex>{{ profile.name }}</v-flex>
          <v-flex>{{ profile.username }}</v-flex>
          <v-flex>{{ profile.address }}</v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-container>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "Profile",
  computed: mapState(['profile'])
}
</script>

<style scoped>

</style>