package com.example.mynotes.models;

public class Note {
        private String title;
        private String content;
        private String time;

        public Note(){}
        public Note(String title,String content, String time){
            this.title = title;
            this.content = content;
        }

        public String getTime() {return time;}

        public void setTime(String time) { this.time = time; }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
}
