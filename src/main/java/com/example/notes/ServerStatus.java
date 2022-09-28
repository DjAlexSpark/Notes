package com.example.notes;

public enum ServerStatus {

        ALIVE("Connected"),
        CONNECTING("Connecting"),
        DEAD("Not Connected");

        private final String displayName;

    ServerStatus(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }

}
