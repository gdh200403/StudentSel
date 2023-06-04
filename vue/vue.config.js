const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer: {
    host: 'localhost',
    port: 8080, // 端口
    proxy: {
      "/proxy_url":{           // /proxy_url 这个用来和根路径 baseURL 进行匹配
        target: 'http://localhost:8081',  // 这个是填写跨域的请求域名+端口号，也就是要请求的URL(不包含URL路径)
        changeOrigin: true,  // 是否允许跨域请求
        pathRewrite: {   // 路径重写
          '^/proxy_url': '/' // 替换target中的请求地址，原请求为 http://127.0.0.1:8080/kuayu 实际请求为 http://127.0.0.1:8080/proxy_url/
        }
      }
    }
  }
})
