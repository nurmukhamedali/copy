<template>
  <v-container>
    <v-form lazy-validation v-model="valid">
      <v-text-field flat outline block
                    v-model="order.recipientName"
                    label="Name"
                    :rules="nameRules"></v-text-field>
      <v-text-field flat outline block
                    v-model="order.recipientPhoneNumber"
                    label="Phone"
                    mask="(###)###-####"
      ></v-text-field>
      <v-text-field flat outline block
                    v-model="order.deliveryAddress"
                    label="Delivery Address"
      ></v-text-field>
      <v-text-field flat single-line outline block
                    v-model="order.deliveryDate"
                    label="Pick a delivery date"
                    readonly
      ></v-text-field>
      <div class="mb-4">
        <v-date-picker full-width no-title
                       :min="minDeliveryDate"
                       :max="maxDeliveryDate"
                       v-model="order.deliveryDate"
        ></v-date-picker>
      </div>
      <v-textarea flat outline
                  v-model="order.deliveryInstructions"
                  label="Delivery Instructions"
      ></v-textarea>
      <v-select outline single-line class=""
                :items="paymentTypes"
                v-model="order.paymentType"
                item-text="type"
                menu-props="auto"
                placeholder="Select a payment type"
                items-value="value"
      ></v-select>
      <v-alert
          :value="isCartEmpty"
          type="error"
      >
        Cart is empty. Let's pick some goods first.
      </v-alert>
      <v-btn block depressed round color="success" @click="placeOrder"
             :disabled="isCartEmpty"
      >Place Order</v-btn>
    </v-form>

  </v-container>
</template>

<script>
import {mapActions, mapState} from "vuex";

export default {
  name: "OrderForm",
  data(){
    return {
      valid: false,
      dateFieldVisible: false,
      isCartEmpty: true,
      minDeliveryDate: null,
      maxDeliveryDate: null,
      paymentTypes: [{type: "Cash", value: "CASH"}],
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
  computed: mapState(['personalInfo']),
  created() {
    this.maxDeliveryDate = this.getOneWeekAfter();
    this.minDeliveryDate = this.getTomorrow();
    this.isCartEmpty = this.personalInfo.cart.items.length < 1;
    this.order.recipientName = this.personalInfo.profile.name;
    this.order.deliveryAddress = this.personalInfo.profile.address;
  },
  methods: {
    ...mapActions(['addOrderAction']),
    placeOrder(){
      if (this.personalInfo.cart.items){
        this.addOrderAction(this.order)
        this.$router.push('/profile')
      } else {
      }
    },
    getTomorrow(){
      let date = new Date()
      return date.toISOString()
    },
    getOneWeekAfter(){
      let date = new Date()
      date.setDate(date.getDate() + 7)
      return date.toISOString()
    }
  }
}
</script>

<style scoped>
.contactInfo{
  width: 15rem
}
</style>