<template>
  <v-flex d-flex xs6 sm4 md3>
    <v-card class="ma-2">
        <v-card-title primary-title>
          <div>
            <div class="headline">
              {{ category.name }}
            </div>
          </div>
        </v-card-title>
      <v-divider></v-divider>
      <v-card-actions>
        <v-btn icon @click="edit"><v-icon>edit</v-icon></v-btn>
        <v-btn icon @click="del"><v-icon>delete</v-icon></v-btn>
      </v-card-actions>
    </v-card>
  </v-flex>
</template>

<script>
import { mapActions } from 'vuex'
export default {
  name: "CategoryCard",
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