FROM node:8-alpine
COPY package.json  ./

RUN npm set progress=false && npm config set depth 0 && npm cache clean --force
RUN npm i && mkdir /ng-app && cp -R ./node_modules ./ng-app

WORKDIR /ng-app

COPY . .

RUN $(npm bin)/ng build --prod --build-optimizer

##Nginx
FROM nginx:1.13.3-alpine

#COPY nginx/default.conf /etc/nginx/conf.d/
RUN rm -rf /usr/share/nginx/html/*

#--from=builder
COPY dist /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]
