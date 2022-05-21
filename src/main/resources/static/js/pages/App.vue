<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title>Pinemelon</v-toolbar-title>
      <v-btn flat
             v-if="profile"
             :disabled="$route.path === '/'"
             @click="showCategories"
      >
        Categories
      </v-btn>
      <v-btn flat
             v-if="profile"
             :disabled="$route.path === '/products'"
             @click="showProducts"
      >
        Products
      </v-btn>
      <v-spacer></v-spacer>
      <v-btn flat
             icon
             v-if="profile"
             :disabled="$route.path === '/cart'"
             @click="showCart"
      >
        <v-icon>shopping_cart</v-icon>
      </v-btn>
      <v-btn flat
             v-if="profile"
             :disabled="$route.path === '/profile'"
             @click="showProfile"
      >
        <v-list-tile-avatar>
          <v-img :src="profile.avatar" round></v-img>
        </v-list-tile-avatar>
        <div class="hidden-sm-and-down">
          {{ profile.username }}
        </div>
      </v-btn>
      <v-btn v-if="profile" icon href="/logout">
        <v-icon>logout</v-icon>
      </v-btn>
    </v-toolbar>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
  import { mapState, mapMutations } from 'vuex'
  import { addHandler } from "util/ws";

  export default {
    computed: mapState(['profile']),
    methods: {
      ...mapMutations(['addProductMutation', 'updateProductMutation', 'removeProductMutation']),
      showProducts(){
        this.$router.push('/products')
      },
      showProfile(){
        this.$router.push('/profile')
      },
      showCategories(){
        this.$router.push('/categories')
      },
      showCart(){
        this.$router.push('/cart')
      }
    },
    created() {
      addHandler(data => {
        if (data.objectType === 'PRODUCT'){
          switch (data.eventType){
            case 'CREATE':
              // this.addProductMutation(data.body)
              break
            case 'UPDATE':
              // this.updateProductMutation(data.body)
              break
            case 'REMOVE':
              // this.removeProductMutation(data.body)
              break
            default:
              console.error(`Looks like the event type is unknown "${data.eventType}"`)
          }
        } else if(data.objectType === 'CATEGORY') {
          switch (data.eventType) {
            case 'CREATE':
              // this.addProductMutation(data.body)
              break
            case 'UPDATE':
              // this.updateProductMutation(data.body)
              break
            case 'REMOVE':
              // this.removeProductMutation(data.body)
              break
            default:
              console.error(`Looks like the event type is unknown "${data.eventType}"`)
          }
        } else {
          console.error(`Looks like the event type is unknown "${data.objectType}"`)
        }
      })
    },
    beforeMount() {
      if (!this.profile){
        this.$router.replace('/auth')
      }
    }
  }
</script>

<style>
.main-app {
  color: maroon;
}
</style>