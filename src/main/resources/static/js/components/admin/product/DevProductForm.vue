<template>
  <v-card class="ma-2 v-btn--round border-1">
    <v-card-text class="d-flex justify-center">New Product</v-card-text>

    <v-text-field class="ma-2"
                  label="Name"
                  placeholder="Enter product name"
                  v-model="name"
    />
    <v-text-field class="ma-2"
                  label="Brand"
                  placeholder="Enter product brand"
                  v-model="brand"
    />
    <v-text-field class="ma-2"
                  label="Price"
                  placeholder="Enter product price"
                  v-model="price"
    />
    <v-text-field class="ma-2"
                  label="URL"
                  placeholder="Enter link to image"
                  v-model="image"
    />
    <v-select
        class="mx-2 pa-0"
        :items="sortedCategories"
        v-model="category"
        item-text="name"
        menu-props="auto"
        placeholder="Select a category"
        return-object
    ></v-select>
    <v-card-actions class="pt-0">
      <v-btn class="ma-2 d-flex"color="success" block @click="post" round depressed>
        Add
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "DevProductForm",
  props: ['productAttr'],
  computed: mapGetters(['sortedCategories']),
  data(){
    return {
      id: null,
      name: '',
      brand: '',
      image: '',
      price: '',
      category: {}
    }
  },
  watch: {
    productAttr: function(newVal, oldVal){
      this.name = newVal.name;
      this.id = newVal.id;
      this.image = newVal.image;
      this.brand = newVal.brand;
      this.price = newVal.price;
      this.category = newVal.category;
    }
  },
  methods: {
    ...mapActions(['addProductAction', 'updateProductAction']),
    post() {
      const product = {
        id: this.id,
        name: this.name,
        image: this.image,
        brand: this.brand,
        price: this.price,
        category: this.category
      };

      if (this.id) {
        this.updateProductAction(product)
      } else {
        this.addProductAction(product)
      }
      // clear form
      this.id = ''
      this.name = ''
      this.image = ''
      this.brand = ''
      this.price = ''
      this.category = {}
    }
  }
}
</script>

<style scoped>

</style>