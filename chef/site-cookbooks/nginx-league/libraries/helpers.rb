module Apt
  # Helpers for apt
  module Helpers
    # Determines if apt is installed on a system.
    #
    # @return [Boolean]
    def apt_installed?
      !which('apt-get').nil?
    end

    # Finds a command in $PATH
    #
    # @return [String, nil]
    def which(cmd)
      ENV["PATH"] = "" if ENV["PATH"].nil?
      paths = (ENV['PATH'].split(::File::PATH_SEPARATOR) + %w(/bin /usr/bin /sbin /usr/sbin))

      paths.each do |path|
        possible = File.join(path, cmd)
        return possible if File.executable?(possible)
      end

      nil
    end
  end
end

Chef::Recipe.send(:include, ::Apt::Helpers)
Chef::Resource.send(:include, ::Apt::Helpers)
Chef::Provider.send(:include, ::Apt::Helpers)
