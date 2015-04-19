#
# Cookbook Name:: nginx-league
# Recipe:: default
#
# Copyright 2014, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

puts "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"
puts "Building: #{node[:proxy][:name]}"
puts "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *"

# TODO move to own recipe
Chef::Log.warn 'apt is not installed. Apt-specific resources will not be executed.' unless apt_installed?

# For other recipes to call to force an update
execute 'apt-get update' do
  command "apt-get update"
  ignore_failure true
end

package 'nginx' do
  action :install
end

package 'vim' do
  action :install
end

templates = %w(/etc/nginx/nginx.conf)
templates.each do |tmpl|
  template tmpl do
    source "#{tmpl}.erb"
    mode "0644"
    action :create
  end
end

templates = %w(/etc/nginx/sites-available/gb-league)
templates.each do |tmpl|
  template tmpl do
    source "#{tmpl}.erb"
    mode "0644"
    action :create
    #notifies :reload, resources(:service => "nginx")
  end
end

link "/etc/nginx/sites-enabled/gb-league" do
  to "/etc/nginx/sites-available/gb-league"
end

service "nginx stop" do
  service_name "nginx"
  action :stop
end