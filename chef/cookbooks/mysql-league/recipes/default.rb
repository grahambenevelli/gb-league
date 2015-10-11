#
# Cookbook Name:: mysql
# Recipe:: default
#
# Copyright 2014, GB - League
#
# All rights reserved - Do Not Redistribute
#

puts "Starting mysql for league"

include_recipe "mysql::client"
include_recipe "mysql::server"

puts "Finishing mysql for league"