# -*- mode: ruby -*-
# vi: set ft=ruby :

# run: vagrant plugin install foobar-plugin
Vagrant.require_plugin "vagrant-omnibus"

chef_repo = './chef/'

Vagrant.configure("2") do |config|
  config.vm.box = "hashicorp/precise64"
  config.omnibus.chef_version = "11.12.2"

  # mount the code directory
  config.vm.synced_folder ".", "/var/www/leagues", :nfs => eval(ENV['VAGRANT_USE_NFS']||"true")

  config.vm.provider :virtualbox do |vb|
    vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
  end

  # can't use NFS here or the node recipe won't work
  #config.vm.synced_folder homes[:code_path][:path], "/var/www/bigfro", :nfs => false

  config.vm.define :league do |league|
    
    private_ip = "192.168.56.8"
    league.vm.network :private_network, ip: private_ip

    league.vm.box = "precise64"
    league.vm.box_url = "http://files.vagrantup.com/precise64.box"
    league.vm.hostname = 'devbox.gb.league.com'
    
    league.hostmanager.aliases = %w(league.dev)
    
    league.vm.provider :virtualbox do |vb|
      vb.name = "devbox.gb.league.com"
      vb.customize ["modifyvm", :id, "--memory", "2048"]
      vb.customize ["modifyvm", :id, "--cpus", "2"]
    end
  
    config.vm.provision :chef_solo do |chef|
      chef.cookbooks_path = File.join(chef_repo, "cookbooks")
      File.join(chef_repo, "cookbooks")
      chef.data_bags_path = File.join(chef_repo, "data_bags")
      chef.roles_path = File.join(chef_repo, "roles")
      chef.provisioning_path = "/tmp/vagrant-chef"

      chef.run_list = [
        "nginx-league",
        "mysql-league"
      ]
      chef.json = {
        "nginx-league" => {},
        "mysql" => {
            "server_root_password" => "808052769e2c6d909027a2905b224bad", 
            "server_debian_password" => "569d1ed2d46870cc020fa87be83af98d", 
            "server_repl_password" => "476911180ee92a2ee5a471f33340f6f4"
        }
      }
    end

    config.vm.provision "shell", inline: "/etc/init.d/nginx restart"
  end

end