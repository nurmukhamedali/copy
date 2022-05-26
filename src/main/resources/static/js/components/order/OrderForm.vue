<template>
  <v-container>
    <v-form lazy-validation v-model="valid">
      <v-text-field flat single-line solo class="contactInfo"
                    v-model="order.recipientName"
                    label="Name"
                    :rules="nameRules"></v-text-field>
      <v-text-field flat single-line solo class="contactInfo"
                    v-model="order.recipientPhoneNumber"
                    label="Phone"
                    mask="(###)###-####"
      ></v-text-field>
      <v-text-field flat single-line solo class="deliveryInfo"
                    v-model="order.deliveryAddress"
                    label="Delivery Address"
      ></v-text-field>
      <v-menu
          v-model="dateFieldVisible"
          :close-on-content-click="false"
          :nudge-right="40"
          lazy
          transition="scale-transition"
          offset-y
          full-width
          min-width="290px"
      >
      </v-menu>
    </v-form>
    <v-btn @click="placeOrder">PlaceOrder</v-btn>
  </v-container>
</template>

<script>
import {mapActions} from "vuex";

export default {
  name: "OrderForm",
  data(){
    return {
      valid: false,
      modal: false,
      dateFieldVisible: false,
      order: {
        recipientName: '',
        recipientPhoneNumber: '',
        deliveryDate: null,
        deliveryInstructions: '',
        deliveryAddress: '',
        paymentType: null,
      },
      nameRules: [
        v => !!v || 'Name is required'
      ],
    }
  },
  methods: {
    ...mapActions(['addOrderAction']),
    placeOrder(){
      this.addOrderAction(this.order)
    }
  }
}
</script>

<style scoped>
.contactInfo{
  width: 15rem
}
.deliveryInfo{
  width: 20rem
}
</style>