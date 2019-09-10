<template>
  <v-container>
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">Video Rental Information</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="8">
        <v-data-table :headers="headers" :items="items" :items-per-page="5" class="elevation-1">
        </v-data-table>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "ViewAppointmentsData",
  data() {
    return {
      headers: [
        { text: "Employee", value: "createdBy.name" },
        {
          text: "Patient",
          align: "left",
          sortable: false,
          value: "appmPatient.name"
        },
        { text: "Duration", value: "duration.name" },
        { text: "Room", value: "room.title" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    // ดึงข้อมูล VideoRental ใส่ combobox
    getAppointments() {
      http
        .get("/appointment")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getAppointments();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getAppointments();
  }
};
</script>
