package guru.qa.model;

import com.google.gson.annotations.SerializedName;

public class Glossary {
    public String title = "example glossary";
    @SerializedName("gloss_div")
    public GlossDiv glossDiv;

    public static class GlossDiv {
        public String title = "S";
        public boolean flag = true;
    }
}
