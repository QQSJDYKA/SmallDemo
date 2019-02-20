package com.eebbk.smalldemo.app.word.bean;

import org.json.JSONObject;

import java.util.List;

/**
 * Author: halo_huang
 * Create: 2019/2/20
 * Describe:
 */
public class BundleJsonBean {
    /**
     * manifest : {"version":"1.0.0","bundles":[{"uri":"main","pkg":"com.eebbk.smalldemo.app.main","rules":{"main2":".Main2"}},{"uri":"word","pkg":"com.eebbk.smalldemo.app.word"}]}
     * updates : [{"pkg":"com.eebbk.smalldemo.app.word","url":"https://github.com/QQSJDYKA/SmallDemo/update/libcom_eebbk_smalldemo_app_word.so"}]
     */

    private JSONObject manifest;
    private List<UpdatesBean> updates;

    public JSONObject getManifest() {
        return manifest;
    }

    public void setManifest(JSONObject manifest) {
        this.manifest = manifest;
    }

    public List<UpdatesBean> getUpdates() {
        return updates;
    }

    public void setUpdates(List<UpdatesBean> updates) {
        this.updates = updates;
    }

    public static class UpdatesBean {
        /**
         * pkg : com.eebbk.smalldemo.app.word
         * url : https://github.com/QQSJDYKA/SmallDemo/update/libcom_eebbk_smalldemo_app_word.so
         */

        private String pkg;
        private String url;

        public String getPkg() {
            return pkg;
        }

        public void setPkg(String pkg) {
            this.pkg = pkg;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
