# FROM <image>[:<tag>|@<digest]
FROM node:12.22.12-alpine as builder

LABEL Fatih Celik <celikfatih@protonmail.com>

COPY package.json package-lock.json ./

RUN npm set progress=false && npm set unsafe-perm true && npm config set depth 0 && npm cache clean --force

WORKDIR /ng-app

COPY . .

RUN npm install

EXPOSE 8000

