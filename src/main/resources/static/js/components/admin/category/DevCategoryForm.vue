<template>
  <div>
    <v-text-field class="ma-2"
                  label="Name"
                  placeholder="Enter category name"
                  v-model="name"
    />
    <v-text-field class="ma-2"
                  label="URL"
                  placeholder="Enter link to image"
                  v-model="image"
    />
    <v-btn class="ma-2" @click="post">
      Add
    </v-btn>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  name: "DevCategoryForm",
  props: ['categoryAttr', 'editor'],
  data(){
    return {
      id: null,
      name: '',
      image: '',
    }
  },
  watch: {
    categoryAttr: function(newVal, oldVal){
      this.name = newVal.name;
      this.id = newVal.id;
      this.image = newVal.image;
    }
  },
  methods: {
    ...mapActions(['addCategoryAction', 'updateCategoryAction']),
    post() {
      const category = {
        id: this.id,
        name: this.name,
        image: this.image,
      };

      if (this.id) {
        this.updateCategoryAction(category)
      } else {
        this.addCategoryAction(category)
      }
      // clear form
      this.id = ''
      this.name = ''
      this.image = ''

    }
  }
}
</script>

<style scoped>

</style>