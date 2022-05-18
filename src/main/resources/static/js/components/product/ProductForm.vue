<template>
  <div>
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
        :items="sortedCategories"
        v-model="category"
        name="category"
        item-text="name"
        return-object
        label="Select a category"
    ></v-select>
    <v-btn class="ma-2" @click="post">
      Add
    </v-btn>
  </div>
</template>

<script>
import { mapActions, mapGetters } from "vuex";

export default {
  name: "ProductForm",
  props: ['productAttr'],
  computed: mapGetters(['sortedCategories']),
  data(){
    return {
      id: null,
      name: '',
      brand: '',
      image: '',
      price: '',
      category: {id: 9}
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
    }
  }
}
</script>

<style scoped>

</style>