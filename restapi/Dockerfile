FROM node:18.13.0
LABEL org.opencontainers.image.source https://github.com/pglez82/ASW2223_0
COPY . /app
WORKDIR /app
#Install the dependencies
RUN npm install
CMD [ "npm", "start" ]