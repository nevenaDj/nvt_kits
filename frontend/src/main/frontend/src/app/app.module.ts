import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'ngx-dropdown';

import { PagerService } from './pagination/pager.service';
import { GlitchTypeService } from './glitch-types/glitch-type.service';
import { UserService } from './users/user.service';
import { CompanyService } from './companies/company.service';
import { TenantService } from './tenants/tenant.service';
import { ApartmentService } from './apartments/apartment.service';
import { BuildingService } from './buildings/building.service';
import { AuthService } from './login/auth.service';
import { AuthGuardService } from './guards/auth-guard.service';
import { RoleGuardService } from './guards/role-guard.service';
import { AppRoutingModule } from './/app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HomeAdminComponent } from './home-admin/home-admin.component';
import { BuildingDetailComponent } from './buildings/building-detail/building-detail.component';
import { BuildingsComponent } from './buildings/buildings.component';
import { AddBuildingComponent } from './buildings/add-building/add-building.component';
import { ApartmentsComponent } from './apartments/apartments.component';
import { AddApartmentComponent } from './apartments/add-apartment/add-apartment.component';
import { AddPresidentComponent } from './buildings/add-president/add-president.component';
import { HomeCompanyComponent } from './home-company/home-company.component';
import { LoginLayoutComponent } from './login/login-layout/login-layout.component';
import { TenantsComponent } from './tenants/tenants.component';
import { ApartmentDetailComponent } from './apartments/apartment-detail/apartment-detail.component';
import { AddTenantComponent } from './tenants/add-tenant/add-tenant.component';
import { TenantDetailComponent } from './tenants/tenant-detail/tenant-detail.component';
import { AddOwnerComponent } from './apartments/add-owner/add-owner.component';
import { CompaniesComponent } from './companies/companies.component';
import { AddCompanyComponent } from './companies/add-company/add-company.component';
import { CompanyDetailComponent } from './companies/company-detail/company-detail.component';
import { UsersComponent } from './users/users.component';
import { GlitchTypesComponent } from './glitch-types/glitch-types.component';
import { AddGlitchTypeComponent } from './glitch-types/add-glitch-type/add-glitch-type.component';
import { HomePresidentComponent } from './home-president/home-president.component';
import { HomeOwnerComponent } from './home-owner/home-owner.component';
import { RegisterComponent } from './register/register.component';
import { AdminProfileComponent } from './users/profile/admin-profile/admin-profile.component';
import { PasswordComponent } from './users/password/password.component';
import { ProfileComponent } from './users/profile/profile.component';
import { ProfileUpdateComponent } from './users/profile/profile-update/profile-update.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HomeAdminComponent,
    BuildingDetailComponent,
    BuildingsComponent,
    AddBuildingComponent,
    ApartmentsComponent,
    AddApartmentComponent,
    AddPresidentComponent,
    HomeCompanyComponent,
    LoginLayoutComponent,
    TenantsComponent,
    ApartmentDetailComponent,
    AddTenantComponent,
    TenantDetailComponent,
    AddOwnerComponent,
    CompaniesComponent,
    AddCompanyComponent,
    CompanyDetailComponent,
    UsersComponent,
    GlitchTypesComponent,
    AddGlitchTypeComponent,
    HomePresidentComponent,
    HomeOwnerComponent,
    RegisterComponent,
    AdminProfileComponent,
    PasswordComponent,
    ProfileComponent,
    ProfileUpdateComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    DropdownModule
    
  ],
  providers: [
    AuthGuardService,
    RoleGuardService,
    AuthService,
    BuildingService,
    ApartmentService,
    TenantService,
    CompanyService,
    UserService,
    GlitchTypeService,
    PagerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
