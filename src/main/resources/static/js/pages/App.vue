<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title v-if="profile" :disabled="$route.path === '/'" @click="showMain" style="cursor: pointer">
        Pinemelon
      </v-toolbar-title>
      <v-btn icon v-if="profile" :disabled="$route.path === '/dev'" @click="showDevMenu">
        <v-icon>settings</v-icon>
      </v-btn>
      <v-spacer></v-spacer>
      <!--Profile-->
      <v-btn flat round v-if="profile" :disabled="$route.path === '/profile'" @click="showProfile" class="text-lowercase">
        <v-avatar size="36" class="ma-2">
          <v-img :src="profile.avatar" round></v-img>
        </v-avatar>
        <div class="hidden-sm-and-down">
          {{ profile.username }}
        </div>
      </v-btn>
      <!--Cart-->
      <v-btn depressed color="success" class=""
             v-if="profile" :disabled="$route.path === '/checkout'" @click="showCart">
        <v-icon class="ma-0">shopping_cart</v-icon>
        <span>{{ cart.totalItems }}</span>
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
    computed: mapState(['profile', 'cart']),
    methods: {
      ...mapMutations(['addProductMutation', 'updateProductMutation', 'removeProductMutation']),
      showProfile(){
        this.$router.push('/profile')
      },
      showCart(){
        this.$router.push('/checkout')
      },
      showMain(){
        this.$router.push('/')
      },
      showDevMenu(){
        this.$router.push('/dev')
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

<style scoped>
.v-btn{
  min-width: auto;
}
</style>