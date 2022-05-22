<template>
  <v-flex d-flex xs6 sm4 md3>
    <v-card flat class="ma-2 v-btn--round border-1">
      <v-card-text>
        <span class="d-flex justify-center">{{ category.name }}</span>
      </v-card-text>
      <v-card-actions>
      <v-layout align-center justify-space-around>
        <v-btn icon color="warning" @click="edit" small>
          <v-icon dark small>edit</v-icon>
        </v-btn>
        <v-btn icon color="error" @click="del" small>
          <v-icon dark small>delete</v-icon>
        </v-btn>
      </v-layout>
      </v-card-actions>
    </v-card>
  </v-flex>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "DevCategoryCard",
  props: ['category', 'editCategory'],
  methods: {
    ...mapActions(['removeCategoryAction']),
    edit() {
      this.editCategory(this.category)
    },
    del() {
      this.removeCategoryAction(this.category)
    }
  }
}
</script>

<style scoped>
</style>