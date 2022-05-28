<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title v-if="personalInfo.profile" :disabled="$route.path === '/'" @click="showMain" style="cursor: pointer">
        Pinemelon
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <!--Profile-->
      <v-btn flat round v-if="personalInfo.profile" :disabled="$route.path === '/profile'" @click="showProfile" class="text-lowercase">
        <v-avatar size="36" class="ma-2">
          <v-img :src="personalInfo.profile.avatar" round></v-img>
        </v-avatar>
        <div class="hidden-sm-and-down">
          {{ personalInfo.profile.username }}
        </div>
      </v-btn>
      <!--Cart-->
      <v-btn depressed color="success" class=""
             v-if="personalInfo.profile" :disabled="$route.path === '/checkout'" @click="showCart">
        <v-icon class="ma-0">shopping_cart</v-icon>
        <span>{{ personalInfo.cart.totalItems }}</span>
      </v-btn>
    </v-toolbar>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import {mapState, mapMutations, mapActions} from 'vuex'
  import { addHandler } from "util/ws";

  export default {
    computed: mapState(['personalInfo']),
    methods: {
      ...mapMutations(['addProductMutation', 'updateProductMutation', 'removeProductMutation']),
      ...mapActions(['loadProfileAction', 'loadCartAction']),
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
      if (!this.personalInfo.profile){
        this.$router.replace('/auth')
      } else {
        // this.loadProfileAction()
        // this.loadCartAction()
      }
    }
  }
</script>

<style scoped>
.v-btn{
  min-width: auto;
}
</style>